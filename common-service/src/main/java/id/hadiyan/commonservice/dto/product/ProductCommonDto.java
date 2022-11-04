package id.hadiyan.commonservice.dto.product;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductCommonDto {
    private UUID id;
    private String name;
}
