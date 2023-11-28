package io.github.euristenede.dto;

/**
 *
 * @author Euristenede Santos
 */
public class Input {

    private Integer month;
    private Integer year;
    private String type;

    public Input(Integer month, Integer year, String type) {
        this.month = month;
        this.year = year;
        this.type = type;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    
}
