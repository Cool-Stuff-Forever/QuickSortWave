package org.csf.Service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;

@Data
@RequiredArgsConstructor
public class ConnectService{
    protected final String chatId;

    public String generateKey(String chatId){
        return DigestUtils.sha256Hex(chatId);
    }
}
