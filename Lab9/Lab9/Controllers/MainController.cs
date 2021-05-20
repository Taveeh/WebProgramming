using System;
using System.Collections.Generic;
using System.Linq;
using System.Web.Http.Cors;
using System.Web.Mvc;
using Lab9.Data;
using Lab9.Models;
using Newtonsoft.Json;

namespace Lab9.Controllers {
    [EnableCors(origins: "*", headers: "*", methods: "*")]
    public class MainController : Controller {
        private DataAbstractLayer _dataAbstractLayer = new DataAbstractLayer();

        // GET
        public ActionResult Index() {
            return View("LoginView");
        }

        public ActionResult Login(string username, string password) {
            List<User> users = _dataAbstractLayer.GetAllUsers()
                .Where(user => user.Username.Equals(username) && user.Password.Equals(password)).ToList();
            if (users.Count.Equals(0)) {
                return View("LoginView");
            }

            HttpContext.Session["user"] = username;
            return Redirect("/Main/Logs");
        }

        public ActionResult Logs() {
            string user = (string) HttpContext.Session["user"];
            if (user == null) {
                return Redirect("/Main/Login");
            }

            return View("Logs");
        }

        // GET
        public string GetAllLogs() {
            string user = (string) HttpContext.Session["user"];
            if (user == null) {
                return JsonConvert.SerializeObject("Error 404");
            }

            List<LogReport> logReports = _dataAbstractLayer.GetAllLogs();
            var list = JsonConvert.SerializeObject(logReports);
            return list;
        }

        // GET
        public string GetLogsByUser() {
            string user = (string) HttpContext.Session["user"];
            if (user == null) {
                return JsonConvert.SerializeObject("Not nice");
            }
            List<LogReport> logReports = _dataAbstractLayer.GetLogsByUser((string) HttpContext.Session["user"]);
            return JsonConvert.SerializeObject(logReports);
        }

        public string GetLogsByType(string type) {
            string user = (string) HttpContext.Session["user"];
            if (user == null) {
                return JsonConvert.SerializeObject("Not nice");
            }
            return JsonConvert.SerializeObject(_dataAbstractLayer.GetLogsByType(type));
        }

        public string GetLogsBySeverity(string severity) {
            string user = (string) HttpContext.Session["user"];
            if (user == null) {
                return JsonConvert.SerializeObject("Not nice");
            }
            return JsonConvert.SerializeObject(_dataAbstractLayer.GetLogsBySeverity(severity));
        }

        public string AddLog(string logType, string severity, string date, string log) {
            string user = (string) HttpContext.Session["user"];
            if (user == null) {
                return JsonConvert.SerializeObject("Not nice");
            }
            _dataAbstractLayer.AddLog(logType, severity, user, date, log);
            return JsonConvert.SerializeObject("Nice");
        }

        public string RemoveLog(int id) {
            string user = (string) HttpContext.Session["user"];
            if (user == null) {
                return JsonConvert.SerializeObject("Not nice");
            }
            _dataAbstractLayer.RemoveLog(id, user);
            return JsonConvert.SerializeObject("Nice");
        }

        public string Logout() {
            HttpContext.Session["user"] = null;
            return "Ok";
        }
    }
}