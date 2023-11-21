import org.json.JSONObject;
import org.json.XML;

import javax.swing.*;

public class XmlJsonConverter {



    public String convertXmlToJson(String xmlInput) {
        try {
            JSONObject jsonObject = XML.toJSONObject(xmlInput);
            String jsonOutput = jsonObject.toString(3);

            return jsonOutput;
        } catch (Exception e) {
            e.printStackTrace();
            return "Erreur lors de la conversion XML vers JSON.";
        }
    }
}
