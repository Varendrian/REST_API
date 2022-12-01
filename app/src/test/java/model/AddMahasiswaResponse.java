package model;

public class AddMahasiswaResponse {
    @SerializedName("message")
    private String message;

    @SerializedName("status")
    private boolean status;

    public String getMessage(){
        return message;
    }

    public boolean isStatus(){
        return status;
    }
}
