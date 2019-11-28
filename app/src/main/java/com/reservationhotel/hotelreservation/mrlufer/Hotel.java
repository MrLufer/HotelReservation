package com.reservationhotel.hotelreservation.mrlufer;

import android.graphics.drawable.Drawable;

public class Hotel  {

    private String title;
    private String categoryId;
    private String description;
    private float lat ,lgt;
    private String imagen;
    private String id;

    public Hotel() {
        super();
    }

    public Hotel(String id ,String imagen, String categoryId, String title, String description , float lat, float lgt) {
        super();
        this.id=id;
        this.imagen = imagen;
        this.lat = lat;
        this.lgt = lgt;
        this.title = title;
        this.description = description;

        this.categoryId = categoryId;
    }


    public String getTitle() {
        return title;
    }

    public void setTittle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLgt() {
        return lgt;
    }

    public void setLgt(float lgt) {
        this.lgt = lgt;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /*

    public Drawable getImage() {
        return imagen;
    }

     */

   /* public void setHotelTitle(Drawable imagen) {
        this.imagen = imagen;
    }

    */

    public String getCategoryId(){return categoryId;}

    public void setCategoryId(String categoryId){this.categoryId = categoryId;}

}