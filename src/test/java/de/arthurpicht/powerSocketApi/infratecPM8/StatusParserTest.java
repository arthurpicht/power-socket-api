package de.arthurpicht.powerSocketApi.infratecPM8;

import de.arthurpicht.powerSocketApi.PowerSocketApiException;
import de.arthurpicht.powerSocketApi.Status;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StatusParserTest {

    private static List<Status.OutletStatus> createListOfOutletStatusExpected() {
        List<Status.OutletStatus> outletStatusListExpected = new ArrayList<>();
        outletStatusListExpected.add(new Status.OutletStatus("1.1", "server-01", false));
        outletStatusListExpected.add(new Status.OutletStatus("1.2", "server-02", false));
        outletStatusListExpected.add(new Status.OutletStatus("1.3", "server-03", true));
        outletStatusListExpected.add(new Status.OutletStatus("1.4", "server-04", true));
        outletStatusListExpected.add(new Status.OutletStatus("2.1", "Out 5", false));
        outletStatusListExpected.add(new Status.OutletStatus("2.2", "Out 6", false));
        outletStatusListExpected.add(new Status.OutletStatus("2.3", "Out 7", false));
        outletStatusListExpected.add(new Status.OutletStatus("2.4", "Out 8", false));
        return outletStatusListExpected;
    }


    @Test
    public void parseLineTest() {
        String string = "server-01: 0<br>server-02: 0<br>server-03: 1<br>server-04: 1<br>Out 5: 0<br>Out 6: 0<br>Out 7: 0<br>Out 8: 0<br>";
        List<Status.OutletStatus> outletStatusListExpected = createListOfOutletStatusExpected();

        List<Status.OutletStatus> outletStatusListAct = StatusParser.parseLine(string, new InfratecPM8OutletIds());

        assertEquals(outletStatusListExpected, outletStatusListAct);
    }

    @Test
    public void parseStatusHtmlTest() throws PowerSocketApiException {
        String html = """
                <html>
                <body>
                Status:<br>
                <br>
                server-01: 0<br>server-02: 0<br>server-03: 1<br>server-04: 1<br>Out 5: 0<br>Out 6: 0<br>Out 7: 0<br>Out 8: 0<br>
                </body>
                </html>
                """;
        List<Status.OutletStatus> outletStatusListExpected = createListOfOutletStatusExpected();
        Status statusExpected = new Status("dummy", outletStatusListExpected);

        Status statusActual = StatusParser.parseStatusHtml("dummy", html, new InfratecPM8OutletIds());

        assertEquals(statusExpected, statusActual);
    }

}