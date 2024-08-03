package com.stc.employeeservice.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ORErrorResponse {
    private ORStatus status;

    public ORErrorResponse(int code,String message) {
        this.status = new ORStatus(code,message);
    }

    @Data
    class ORStatus{
        private int code;
        private String message;

        ORStatus(int code,String message){
            this.code = code;
            this.message = message;
        }
    }

}
