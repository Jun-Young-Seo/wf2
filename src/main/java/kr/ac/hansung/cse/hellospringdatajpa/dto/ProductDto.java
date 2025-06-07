package kr.ac.hansung.cse.hellospringdatajpa.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import kr.ac.hansung.cse.hellospringdatajpa.entity.Product;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
//엔티티에서 바로 검증해도 되지만 역할분리를 위해 추가한 Dto
public class ProductDto {
    private Long id;

    @NotBlank(message = "상품명을 입력해주세요.")
    private String name;

    private String brand;

    private String madeIn;

    @NotNull(message = "가격을 입력해주세요.")
    @DecimalMin(value = "0.0", inclusive = false, message = "가격은 0 보다 커야 합니다.")
    private Double price;

    public Product dtoToProduct() {
        Product product = new Product();
        product.setId(this.getId());
        product.setName(this.getName());
        product.setBrand(this.getBrand());
        product.setMadeIn(this.getMadeIn());
        product.setPrice(this.getPrice());
        return product;
    }
}
