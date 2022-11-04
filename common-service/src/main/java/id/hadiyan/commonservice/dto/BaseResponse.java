package id.hadiyan.commonservice.dto;

import lombok.*;

@Value
@Builder
public class BaseResponse {
    String message;
    String errorMessage;
    Object data;
}
