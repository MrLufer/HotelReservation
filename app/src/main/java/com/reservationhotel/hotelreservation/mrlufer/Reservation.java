package com.reservationhotel.hotelreservation.mrlufer;

public class Reservation {

    private String hotelId;
    private String date;
    private String price;
    private String hotelTitle;
    private String id;

    public Reservation() {
        super();
    }

    public Reservation(String id , String hotelTitle, String date, String hotelId, String price) {
        super();
        this.id=id;
        this.hotelTitle = hotelTitle;
        this.hotelId = hotelId;
        this.price = price;
        this.date = date;
    }


    public String getHotelId() {
        return hotelId;
    }

    public void setTittle(String title) {
        this.hotelId = title;
    }

    public String getPrice() {
        return price;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelTitle() {
        return hotelTitle;
    }

    public void setHotelTitle(String hotelTitle) {
        this.hotelTitle = hotelTitle;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDate(){return date;}

    public void setDate(String date){this.date = date;}

}