
namespace Lab9.Models {
    public class LogReport {
        private int _id;
        private string _type;
        private string _severity;
        private string _date;
        private string _username;
        private string _log;

        public LogReport(string type, string severity, string date, string username, string log) {
            this._type = type;
            this._severity = severity;
            this._date = date;
            this._username = username;
            this._log = log;
        }

        public LogReport(int id, string type, string severity, string date, string username, string log) {
            this._id = id;
            this._type = type;
            this._severity = severity;
            this._date = date;
            this._username = username;
            this._log = log;
        }
        public int ID {
            get => _id;
        }
        public string Type {
            get => _type;
            set => _type = value;
        }

        public string Severity {
            get => _severity;
            set => _severity = value;
        }

        public string Date {
            get => _date;
            set => _date = value;
        }

        public string Username {
            get => _username;
            set => _username = value;
        }

        public string Log {
            get => _log;
            set => _log = value;
        }
    }
}