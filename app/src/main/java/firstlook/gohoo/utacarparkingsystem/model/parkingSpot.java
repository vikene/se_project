package firstlook.gohoo.utacarparkingsystem.model;

import org.parceler.Parcel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Parcel
public class parkingSpot {
    String key;
    String starttime;
    String duration;
    String area;
    String floor;
    String type;
    String spot;
    Boolean camera;
    Boolean cart;
    Boolean history;
    String username;
    Boolean isavailable;
    String date;
    String voilations;

    public String getVoilations() {
        return voilations;
    }

    public void setVoilations(String voilations) {
        this.voilations = voilations;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
    public Boolean getIsavailable() {
        return isavailable;
    }

    public void setIsavailable(Boolean isavailable) {
        this.isavailable = isavailable;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public parkingSpot(){

    }
    public parkingSpot(String starttime, String duration, String area
    ,String floor,String type,String spot, Boolean camera, Boolean cart, Boolean history){
        this.starttime = starttime;
        this.duration = duration;
        this.area = area;
        this.floor = floor;
        this.type = type;
        this.spot = spot;
        this.camera = camera;
        this.cart = cart;
        this.history = history;
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        this.isavailable = Boolean.TRUE;
        Date date = new Date();
        this.date = dateFormat.format(date);
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSpot() {
        return spot;
    }

    public void setSpot(String spot) {
        this.spot = spot;
    }

    public Boolean getCamera() {
        return camera;
    }

    public void setCamera(Boolean camera) {
        this.camera = camera;
    }

    public Boolean getCart() {
        return cart;
    }

    public void setCart(Boolean cart) {
        this.cart = cart;
    }

    public Boolean getHistory() {
        return history;
    }

    public void setHistory(Boolean history) {
        this.history = history;
    }

}
