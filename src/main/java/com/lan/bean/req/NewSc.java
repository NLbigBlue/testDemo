package com.lan.bean.req;

import com.lan.bean.pojo.Course;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class NewSc extends Course {
    private Integer grade;
    private Integer sid;
}
