/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

/**
 *
 * @author Nathanalex
 */
public class CustomHttpResponse {
    private int statusCode;
    private String body;
    
    public CustomHttpResponse() {
        
    }
    
    public CustomHttpResponse(int statusCode, String body) {
        this.statusCode = statusCode;
        this.body = body;
    }
}
