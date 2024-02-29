package org.chaitu.spec;

import java.net.InetAddress;
import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import oshi.SystemInfo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
public class MainView {

    @GetMapping("/")
    @ResponseBody
    public String index() {
        return constructJSON();
    }

    private String constructJSON() {
        SystemInfo systemInfo = new SystemInfo();

        Map<String, String> dict = new LinkedHashMap<>();
        dict.put("environment", getenv());
        dict.put("version", getVersion());
        dict.put("mongodb_connected", String.valueOf(checkMongoConnection()));
        dict.put("host", getHostname());
        dict.put("uptime", getUptime(systemInfo));
        dict.put("developer", "chaitanya chandra (chay@outlook.in)");

        ObjectMapper objectMapper = new ObjectMapper();
        String json;
        try {
            json = objectMapper.writeValueAsString(dict);
        } catch (JsonProcessingException e) {
            json = "{\"error\": \"Failed to serialize dictionary to JSON\"}";
        }

        return json;
    }

    private String getHostname() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (Exception e) {
            return "Unknown";
        }
    }

    private String getenv() {
        try {
            return System.getenv().getOrDefault("ENV", "test");
        } catch (Exception e) {
            return "Unknown";
        }
    }

    private String getVersion() {
        try {
            return System.getenv().getOrDefault("VERSION", "0.0");
        } catch (Exception e) {
            return "Unknown";
        }
    }

    private String getUptime(SystemInfo systemInfo) {
        long uptimeSeconds = systemInfo.getOperatingSystem().getSystemUptime();
        return Duration.ofSeconds(uptimeSeconds).toString();
    }

    private boolean checkMongoConnection() {
        // Add your MongoDB connection checking logic here
        return false;
    }
}
