using System;
using System.Collections.Generic;
using Lab9.Models;
using Npgsql;

namespace Lab9.Data {
    public class DataAbstractLayer {
        private static string host = "localhost";
        private static string username = "postgres";
        private static string password = "branza123";
        private static string database = "LogReports";
        private string connectionString = $"Host={host};Username={username};Password={password};Database={database}";

        public DataAbstractLayer() {
        }

        public List<LogReport> GetAllLogs() {
            var conn = new NpgsqlConnection(connectionString);
            conn.Open();
            var sql = "SELECT * FROM Logs";

            List<LogReport> logReports = new List<LogReport>();
            var cmd = new NpgsqlCommand(sql, conn);

            NpgsqlDataReader npgsqlDataReader = cmd.ExecuteReader();
            while (npgsqlDataReader.Read()) {
                logReports.Add(new LogReport(
                    npgsqlDataReader.GetInt32(0),
                    npgsqlDataReader.GetString(1),
                    npgsqlDataReader.GetString(2),
                    npgsqlDataReader.GetString(3),
                    npgsqlDataReader.GetString(4),
                    npgsqlDataReader.GetString(5)
                ));
            }

            return logReports;
        }

        public List<User> GetAllUsers() {
            var conn = new NpgsqlConnection(connectionString);
            conn.Open();
            var sql = "SELECT * FROM Users";

            List<User> users = new List<User>();
            var cmd = new NpgsqlCommand(sql, conn);

            NpgsqlDataReader npgsqlDataReader = cmd.ExecuteReader();
            while (npgsqlDataReader.Read()) {
                users.Add(new User(npgsqlDataReader.GetString(1), npgsqlDataReader.GetString(2)));
            }

            return users;
        }

        public List<LogReport> GetLogsByUser(string user) {
            var conn = new NpgsqlConnection(connectionString);
            conn.Open();
            var sql = "SELECT * FROM Logs L WHERE L.username = \'" + user + "\'";

            List<LogReport> logs = new List<LogReport>();
            var cmd = new NpgsqlCommand(sql, conn);

            NpgsqlDataReader npgsqlDataReader = cmd.ExecuteReader();
            while (npgsqlDataReader.Read()) {
                logs.Add(new LogReport(
                    npgsqlDataReader.GetInt32(0),
                    npgsqlDataReader.GetString(1),
                    npgsqlDataReader.GetString(2),
                    npgsqlDataReader.GetString(3),
                    npgsqlDataReader.GetString(4),
                    npgsqlDataReader.GetString(5)
                ));
            }

            return logs;
        }

        public List<LogReport> GetLogsBySeverity(string severity) {
            var conn = new NpgsqlConnection(connectionString);
            conn.Open();
            var sql = "SELECT * FROM Logs L WHERE L.severity = \'" + severity + "\'";

            List<LogReport> logs = new List<LogReport>();
            var cmd = new NpgsqlCommand(sql, conn);

            NpgsqlDataReader npgsqlDataReader = cmd.ExecuteReader();
            while (npgsqlDataReader.Read()) {
                logs.Add(new LogReport(
                    npgsqlDataReader.GetInt32(0),
                    npgsqlDataReader.GetString(1),
                    npgsqlDataReader.GetString(2),
                    npgsqlDataReader.GetString(3),
                    npgsqlDataReader.GetString(4),
                    npgsqlDataReader.GetString(5)
                ));
            }

            return logs;
        }

        public List<LogReport> GetLogsByType(string type) {
            var conn = new NpgsqlConnection(connectionString);
            conn.Open();
            var sql = "SELECT * FROM Logs L WHERE L.type = \'" + type + "\'";

            List<LogReport> logs = new List<LogReport>();
            var cmd = new NpgsqlCommand(sql, conn);

            NpgsqlDataReader npgsqlDataReader = cmd.ExecuteReader();
            while (npgsqlDataReader.Read()) {
                logs.Add(new LogReport(
                    npgsqlDataReader.GetInt32(0),
                    npgsqlDataReader.GetString(1),
                    npgsqlDataReader.GetString(2),
                    npgsqlDataReader.GetString(3),
                    npgsqlDataReader.GetString(4),
                    npgsqlDataReader.GetString(5)
                ));
            }

            return logs;
        }

        public string AddLog(string type, string severity, string user, string date, string log) {
            var conn = new NpgsqlConnection(connectionString);
            conn.Open();
            var sql =
                "INSERT INTO Logs(type, severity, date, username, actualLog) VALUES (@type, @severity, @date, @username, @actualLog)";
            var cmd = new NpgsqlCommand(sql, conn);
            cmd.Parameters.AddWithValue("type", type);
            cmd.Parameters.AddWithValue("severity", severity);
            cmd.Parameters.AddWithValue("username", user);
            cmd.Parameters.AddWithValue("actualLog", log);
            cmd.Parameters.AddWithValue("date", date);
            cmd.Prepare();

            cmd.ExecuteNonQuery();

            return "Nice";
        }

        public string RemoveLog(int id, string username) {
            var conn = new NpgsqlConnection(connectionString);
            conn.Open();
            var sql = "DELETE FROM Logs WHERE id = @id AND username = @username";
            var cmd = new NpgsqlCommand(sql, conn);
            cmd.Parameters.AddWithValue("id", id);
            cmd.Parameters.AddWithValue("username", username);
            cmd.ExecuteNonQuery();
            return "Nice";
        }
    }
}