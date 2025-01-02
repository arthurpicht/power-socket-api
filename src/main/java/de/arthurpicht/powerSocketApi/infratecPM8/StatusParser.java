package de.arthurpicht.powerSocketApi.infratecPM8;

import de.arthurpicht.powerSocketApi.PowerSocketApiException;
import de.arthurpicht.powerSocketApi.Status;
import de.arthurpicht.powerSocketApi.helper.StringHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StatusParser {

    public static Status parseStatusHtml(String deviceId, String html) throws PowerSocketApiException {
        List<String> htmlLines = StringHelper.splitByLineBreaks(html);
        if (htmlLines.size() < 7 || !htmlLines.get(2).startsWith("Status:"))
            throw new PowerSocketApiException("Could not process html response. Status page expected. Actually is:\n" + html);

        String statusLine = htmlLines.get(4);
        List<Status.OutletStatus> outletStatusList = parseLine(statusLine);
        return new Status(deviceId, outletStatusList);
    }

    public static Status parseActionHtml(String deviceId, String html) throws PowerSocketApiException {
        List<String> htmlLines = StringHelper.splitByLineBreaks(html);
        if (htmlLines.size() > 3 && htmlLines.get(2).startsWith("Status:"))
            throw new PowerSocketApiException("Unexpected response from power module. Check credentials.");
        if (htmlLines.size() < 7 || !htmlLines.get(2).startsWith("Done."))
            throw new PowerSocketApiException("Could not process html response. \"Done\"-page expected. Actually is:\n" + html);
        String statusLine = htmlLines.get(4);
        List<Status.OutletStatus> outletStatusList = parseLine(statusLine);
        return new Status(deviceId, outletStatusList);
    }

    public static List<Status.OutletStatus> parseLine(String string) {
        String[] outlets = string.split("<br>");
        if (outlets.length != 8)
            throw new IllegalStateException("Exactly 8 outlet status infos expected.");
        InfratecConsts.OutletId[] outletIds = InfratecConsts.OutletId.values();
        List<Status.OutletStatus> outletStatusList = new ArrayList<>();
        for (int i = 0; i<8; i++) {
            InfratecConsts.OutletId outletId = outletIds[i];
            String outlet = outlets[i];
            outlet = outlet.trim();
            String[] outletSplit = StringHelper.splitOnLastOccurrence(outlet, ":");
            String outletName = outletSplit[0];
            boolean power = Objects.equals(outletSplit[1].trim(), "1");
            Status.OutletStatus outletStatus = new Status.OutletStatus(outletId.name(), outletName, power);
            outletStatusList.add(outletStatus);
        }
        return outletStatusList;
    }

}
