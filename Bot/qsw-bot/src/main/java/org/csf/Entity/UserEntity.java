package org.csf.Entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * A class for user
 * It helps us to use values of some vars in code
 */
@Data
@Accessors(chain = true)
public class UserEntity {
    private Long chatId;
    private String name;
    private String keySSH;
    private Date registrationDate;
}

