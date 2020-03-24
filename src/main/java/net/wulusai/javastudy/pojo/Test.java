package net.wulusai.javastudy.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author: wulusai
 * @PackageName: net.wulusai.pojo
 * @ClassName: Test
 * @Description:
 * @date: 2020/3/16 15:01
 */
@Table(name = "test")
public class Test implements Serializable {
    @Id
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "number")
    private Long number;

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
