/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author nille
 */
public class MsgDTO {

    String message;
    int code;

    public MsgDTO(String message, int code) {
        this.message = message;
        this.code = code;
    }

}
