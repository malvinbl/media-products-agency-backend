package es.mbl_cu.mpa.application.service;

import org.springframework.stereotype.Service;

@Service
public class TimeFormatterService {

    public String formatDuration(Integer totalSeconds) {
        if (totalSeconds == null) {
            return null;
        }

        int hours = totalSeconds / 3600;
        int minutes = (totalSeconds % 3600) / 60;
        int seconds = totalSeconds % 60;

        StringBuilder sb = new StringBuilder();

        if (totalSeconds < 60) {
            if (seconds < 10) sb.append("0");
            sb.append(seconds);
        } else if (totalSeconds < 3600) {
            if (minutes < 10) sb.append("0");
            sb.append(minutes).append(":");
            if (seconds < 10) sb.append("0");
            sb.append(seconds);
        } else {
            if (hours < 10) sb.append("0");
            sb.append(hours).append(":");
            if (minutes < 10) sb.append("0");
            sb.append(minutes).append(":");
            if (seconds < 10) sb.append("0");
            sb.append(seconds);
        }

        return sb.toString();
    }

}
