package com.example.backend.ApiResponse;


public class ApiResponse<T> {
    private boolean success;
    private String message;
    private T data;



    public ApiResponse(boolean success, String message, T details) {
        this.success = success;
        this.message = message;
        this.data =  details;
    }
    public boolean isSuccess() {
        return success;
    }
    public void setSucsess(boolean success) {
        this.success = success;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }
    
}
