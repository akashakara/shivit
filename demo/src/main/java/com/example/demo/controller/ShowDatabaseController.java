package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.*;

@RestController
@RequestMapping("/db")
public class ShowDatabaseController {

    private static final String DB_URL = "jdbc:mysql://216.48.176.223:3306/";
    private static final String USER = "jacksparrow";
    private static final String PASS = "jajt@$23dh7jg";

    @GetMapping("/hello")
public String test() {
    return "Hello, controller is working!";
}

    @GetMapping("/databases")
    public List<String> showDatabases() {
        List<String> dbs = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            ResultSet rs = conn.createStatement().executeQuery("SHOW DATABASES");
            while (rs.next()) {
                dbs.add(rs.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
            dbs.add("Error: " + e.getMessage());
        }
        return dbs;
    }

    @GetMapping("/tables")
    public List<String> showTables(@RequestParam String database) {
        List<String> tables = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL + database, USER, PASS)) {
            ResultSet rs = conn.createStatement().executeQuery("SHOW TABLES");
            while (rs.next()) {
                tables.add(rs.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
            tables.add("Error: " + e.getMessage());
        }
        return tables;
    }

    @GetMapping("/columns")
    public List<Map<String, String>> showColumns(@RequestParam String database, @RequestParam String table) {
        List<Map<String, String>> columns = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL + database, USER, PASS)) {
            ResultSet rs = conn.createStatement().executeQuery("DESCRIBE " + table);
            while (rs.next()) {
                Map<String, String> col = new LinkedHashMap<>();
                col.put("Field", rs.getString("Field"));
                col.put("Type", rs.getString("Type"));
                col.put("Null", rs.getString("Null"));
                col.put("Key", rs.getString("Key"));
                col.put("Default", rs.getString("Default"));
                col.put("Extra", rs.getString("Extra"));
                columns.add(col);
            }
        } catch (Exception e) {
            e.printStackTrace();
            columns.add(Map.of("Error", e.getMessage()));
        }
        return columns;
    }

    @GetMapping("/preview")
    public List<Map<String, Object>> previewTable(@RequestParam String database, @RequestParam String table, @RequestParam(defaultValue = "5") int limit) {
        List<Map<String, Object>> rows = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL + database, USER, PASS)) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + table + " LIMIT " + limit);
            ResultSetMetaData meta = rs.getMetaData();
            int colCount = meta.getColumnCount();
            while (rs.next()) {
                Map<String, Object> row = new LinkedHashMap<>();
                for (int i = 1; i <= colCount; i++) {
                    row.put(meta.getColumnName(i), rs.getObject(i));
                }
                rows.add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
            rows.add(Map.of("Error", e.getMessage()));
        }
        return rows;
    }
}
