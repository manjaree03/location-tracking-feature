package com.location_tracking.manjaree_project.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonResponseDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Boolean isSuccess;
    private String message;
    private transient Object details;


    public CommonResponseDTO() {
    }

    public CommonResponseDTO(Boolean isSuccess, String message, Object details) {

        this.isSuccess = isSuccess;
        this.message = message;
        this.details = details;
    }

    public CommonResponseDTO(String message2, Object object) {
        this.message = message2;
        this.details = object;
    }


}
