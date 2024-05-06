package com.example.web3;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;

@ApplicationScoped
@Named("clockBean")
public class ClockBean implements Serializable {
    public int[] getTime() {
        LocalTime now = LocalTime.now(ZoneId.systemDefault());
        return new int[]{now.getHour(), now.getMinute(), now.getSecond()};
    }

    public int[] getDate() {
        LocalDate now = LocalDate.now(ZoneId.systemDefault());
        return new int[]{now.getYear(), now.getMonthValue(), now.getDayOfMonth()};
    }
    public String getHtmlContent() {
        int[] date = getDate();
        StringBuilder text = new StringBuilder("<svg width='400' height='400'>");
        text.append("<path d='M 139 250 L 261 250 Q 276 250 276 265 L 276 286 Q 276 301 261 301 L 139 301 Q 124 301 124 286 L 124 265 Q 124 250 139 250' fill='none' stroke='#41423b' stroke-width='4' />");
        text.append("<text fill='#41423b' x='134' y='285' font-size='30' font-weight='bold'>").append(String.format("%02d.%02d.%s", date[2], date[1], date[0])).append("</text>");
        for (int i = 0; i < 12; i++) {
            double x = 200 + 170 * Math.cos(Math.PI / 6 * i);
            double y = 200 + 170 * Math.sin(Math.PI / 6 * i);
            text.append("<circle cx='").append(x).append("' cy='").append(y).append("' r='11' fill='#41423b'></circle>");
        }
        int[] time = getTime();
        int[] thickness = new int[]{7, 5, 3};
        int[] length = new int[]{100, 150, 150};
        ArrayList<String[]> colors = new ArrayList<>();
        colors.add(new String[]{"rgba(0,0,0,0.5)", "'41423b'"});
        colors.add(new String[]{"rgba(0,0,0,0.5)", "'41423b'"});
        colors.add(new String[]{"rgba(0,0,0,0.5)", "'red'"});
        text.append("<circle cx='200' cy='200' r='30' fill='#41423b'></circle>");
        double angle = Math.PI / 6 * time[0] + Math.PI / 6 / 60 * time[1];
        double v = length[0] * Math.cos(angle - Math.PI / 2);
        double v1 = length[0] * Math.sin(angle - Math.PI / 2);
        for (int j = 0; j < 2; j++) {
            text.append(" <path d='M ")
                    .append(200 + thickness[0] * Math.cos(angle)).append(" ")
                    .append(200 + (1 - j) * 6 + thickness[0] * Math.sin(angle))
                    .append(" L ")
                    .append(200 + v + thickness[0] / 2f * Math.cos(angle)).append(" ")
                    .append(200 + (1 - j) * 6 + v1 + thickness[0] / 2f * Math.sin(angle))
                    .append(" L ")
                    .append(200 + v + thickness[0] / 2f * Math.cos(angle - Math.PI)).append(" ")
                    .append(200 + (1 - j) * 6 + v1 + thickness[0] / 2f * Math.sin(angle - Math.PI))
                    .append(" L ")
                    .append(200 + thickness[0] * Math.cos(angle - Math.PI)).append(" ")
                    .append(200 + (1 - j) * 6 + thickness[0] * Math.sin(angle - Math.PI))
                    .append(" Z' fill=").append(colors.get(0)[j]);

            text.append(j == 0 ? " filter='blur(3px)' /> " : " /> ");
        }
        for (int i = 1; i < 3; i++) {
            angle = Math.PI / 30 * time[i];
            v = length[i] * Math.cos(angle - Math.PI / 2);
            v1 = length[i] * Math.sin(angle - Math.PI / 2);
            for (int j = 0; j < 2; j++) {
                text.append(" <path d='M ")
                        .append(200 + thickness[i] * Math.cos(angle)).append(" ")
                        .append(200 + (1 - j) * 6 + thickness[i] * Math.sin(angle))
                        .append(" L ")
                        .append(200 + v + thickness[i] / 2f * Math.cos(angle)).append(" ")
                        .append(200 + (1 - j) * 6 + v1 + thickness[i] / 2f * Math.sin(angle))
                        .append(" L ")
                        .append(200 + v + thickness[i] / 2f * Math.cos(angle - Math.PI)).append(" ")
                        .append(200 + (1 - j) * 6 + v1 + thickness[i] / 2f * Math.sin(angle - Math.PI))
                        .append(" L ")
                        .append(200 + thickness[i] * Math.cos(angle - Math.PI)).append(" ")
                        .append(200 + (1 - j) * 6 + thickness[i] * Math.sin(angle - Math.PI))
                        .append(" Z' fill=").append(colors.get(i)[j]);

                text.append(j == 0 ? " filter='blur(3px)' /> " : " /> ");
            }
        }
        return text.append("</svg>").toString();
    }
}
