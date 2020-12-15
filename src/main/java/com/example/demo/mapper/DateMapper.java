package com.example.demo.mapper;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

@Component
public class DateMapper {
    public OffsetDateTime asOffsetDateTime(Timestamp ts){
        if (ts != null){
            return OffsetDateTime.of(ts.toLocalDateTime(), ZoneOffset.UTC);
        }else{
            return null;
        }
    }
    public Timestamp asTimeStamp(OffsetDateTime offsetDateTime) {
        if (offsetDateTime != null) {
            return Timestamp.valueOf(offsetDateTime.atZoneSameInstant(ZoneOffset.UTC).toLocalDateTime());
        } else {
            return null;
        }
    }
}
