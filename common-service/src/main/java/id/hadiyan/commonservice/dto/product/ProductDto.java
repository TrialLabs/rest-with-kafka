package id.hadiyan.commonservice.dto.product;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto extends ProductCommonDto {
    private String name;
    private String category;
    private BigDecimal price;
    private String description;
}
