package seang.spring.testingmvc.model.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Product {
    private Integer id;
    private String name;
    private String gender;
    private String surname;
    private String email;
    private String phone;
    private String password;
}
