package com.rishi.sit.utils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;

public class DbUtil {
  private static final String URL = "jdbc:sqlite:testdata/sit.db";

  public static void seed() throws Exception {
    try (Connection c = DriverManager.getConnection(URL); Statement s = c.createStatement()) {
      for (String sql : Files.readString(Path.of("testdata/seed.sql")).split(";")) {
        if (!sql.isBlank()) s.execute(sql);
      }
    }
  }

  public static Map<String, String> getUser(int id) throws Exception {
    try (Connection c = DriverManager.getConnection(URL);
         PreparedStatement p = c.prepareStatement("SELECT * FROM users WHERE id=?")) {
      p.setInt(1, id);
      ResultSet r = p.executeQuery();
      r.next();
      return Map.of("name", r.getString("name"),
                    "email", r.getString("email"),
                    "wifi_name", r.getString("wifi_name"));
    }
  }
}