package com.example.estok_app.api_estok_app.json;

public class ImageResponse {
   private int status;
   private String message;
   private String url;

   public ImageResponse(int status, String message, String url) {
        this.status = status;
        this.message = message;
        this.url = url;
   }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

   
}
