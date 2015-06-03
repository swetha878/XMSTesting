/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmstesting;

/**
 *
 * @author ssatyana
 */
public class MsmlCall {

    // change to xml beans/jaxb objects
    public static String buildPlayMsml(String fileName) {
        String msml = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n"
                + "<msml version=\"1.1\">\n"
                + "<dialogstart target=\"conn:1234\" type=\"application/moml+xml\" name=\"DIALOG:AudioPlay\">\n"
                + "	<play >\n"
                + "		<audio uri=\"" + fileName + "\" />\n"
                + "	</play>\n"
                + "</dialogstart>\n"
                + "</msml>";
        return msml;

    }

    public static String buildRecordMsml(String filename, int time) {
        String msml = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n"
                + "<msml version=\"1.1\">\n"
                + "<dialogstart target=\"conn:1234\" type=\"application/moml+xml\">\n"
                + "	<record beep=\"true\" dest=\"" + filename + "\" format=\"audio/wav\" maxtime=\"" + time + "s\">\n"
                + "		<recordexit>\n"
                + "			<send target=\"group\" event=\"terminate\"/>\n"
                + "		</recordexit>\n"
                + "	</record>\n"
                + "</dialogstart>\n"
                + "</msml>";
        return msml;
    }
}
