package cybersoft.java16.ecom.product.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubCategoryDTO {
	@NotBlank(message = "{subcategory.name.notnull}")
	private String name;
}
