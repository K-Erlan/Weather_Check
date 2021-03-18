package sample;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.json.JSONObject;

public class Controller  {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField city;

    @FXML
    private Button getData;

    @FXML
    private Text temp_info;

    @FXML
    private Text temp_temp;

    @FXML
    private Text temp_feel;

    @FXML
    private Text temp_max;

    @FXML
    private Text temp_min;

    @FXML
    private Text temp_pres;

    @FXML
    void initialize() {
        getData.setOnAction(event -> {
            String getUserCity = city.getText().trim();
            if(!getUserCity.equals("")) {
                String output = getUrlContent("http://api.openweathermap.org/data/2.5/weather?q=" + getUserCity + "&appid=e174a606afeb32e8177dfa2ec35cc85d&units=metric");

                if (!output.isEmpty()) {
                    JSONObject obj = new JSONObject(output);
                    temp_temp.setText("TEMPERATURE: " + obj.getJSONObject("main").getDouble("temp") + " °C");
                    temp_feel.setText("FEELS: " + obj.getJSONObject("main").getDouble("feels_like") + " °C");
                    temp_max.setText("MAXIMUM: " + obj.getJSONObject("main").getDouble("temp_max") + " °C");
                    temp_min.setText("MINIMUM: " + obj.getJSONObject("main").getDouble("temp_min") + " °C");
                    temp_pres.setText("PRESSURE: " + obj.getJSONObject("main").getDouble("pressure") + " hPa");
                }else{
                    temp_info.setText("INCORRECT CITY!");
                }
            }
        });
    }

    private static String getUrlContent(String urlAdress) {
        StringBuffer content = new StringBuffer();

        try {
            URL url = new URL(urlAdress);
            URLConnection urlConn = url.openConnection();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            String line;

            while((line = bufferedReader.readLine()) != null) {
                content.append(line + "\n");
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println("Incorrect city!");
        }
        return content.toString();
    }
}
