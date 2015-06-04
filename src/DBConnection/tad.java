/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DBConnection;

/**
 *
 * @author TAD
 */
public class tad {
    private int _Id;
    private String _Outlook;
    private String _Temperature;
    private String _Humidity;
    private String _Windy;
    private String _Classes;

    public tad() {
    }

    public String getClasses() {
        return _Classes;
    }

    public void setClasses(String _Classes) {
        this._Classes = _Classes;
    }

    public String getHumidity() {
        return _Humidity;
    }

    public void setHumidity(String _Humidity) {
        this._Humidity = _Humidity;
    }

    public int getId() {
        return _Id;
    }

    public void setId(int _Id) {
        this._Id = _Id;
    }

    public String getOutlook() {
        return _Outlook;
    }

    public void setOutlook(String _Outlook) {
        this._Outlook = _Outlook;
    }

    public String getTemperature() {
        return _Temperature;
    }

    public void setTemperature(String _Temperature) {
        this._Temperature = _Temperature;
    }

    public String getWindy() {
        return _Windy;
    }

    public void setWindy(String _Windy) {
        this._Windy = _Windy;
    }
    
    
}
