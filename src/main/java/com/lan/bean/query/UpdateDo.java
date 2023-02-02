package com.lan.bean.query;

import com.lan.bean.pojo.Student;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class UpdateDo extends Student {
    Integer oldId;
}
