package com.reservationhotel.hotelreservation.mrlufer;

import android.graphics.drawable.Drawable;

public class Hotel  {

    private String title;
    private String categoryId;
    private String description;
    private float lat ,lgt;
   // private Drawable imagen;

    public Hotel() {
        super();
    }

    public Hotel(String categoryId, String title, String description ) {
        super();
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

    public void setDescription(String description) {
        this.description = description;
    }

    /*

    public Drawable getImage() {
        return imagen;
    }

     */

   /* public void setImagen(Drawable imagen) {
        this.imagen = imagen;
    }

    */

    public String getCategoryId(){return categoryId;}

    public void setCategoryId(String categoryId){this.categoryId = categoryId;}

}