package com.example.ktra_17_04_2020;

public class Weather {
    private String city;
    private int temporary;
    private int image;
    private String typeWeather;

    public String getTypeWeather() {
        return typeWeather;
    }

    public void setTypeWeather(String typeWeather) {
        this.typeWeather = typeWeather;
    }




    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getTemporary() {
        return temporary;
    }

    public void setTemporary(int temporary) {
        this.temporary = temporary;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }


    public Weather(String city, int temporary, int image,String typeWeather) {
        this.city = city;
        this.temporary = temporary;
        this.image = image;
        this.typeWeather = typeWeather;
    }

}
