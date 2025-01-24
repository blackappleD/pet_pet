package com.pet.pet.core.dto.base;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageDTO<T> {
    private Long total;
    private Collection<T> records;
    private Integer page;
    private Integer size;

    public static <P,T> PageDTO<T> from(Page<P> page,Function<P,T> convert){
        PageDTO<T> d=new PageDTO<>();
        d.setPage(page.getNumber());
        d.setSize(page.getSize());
        d.setTotal(page.getTotalElements());
        d.setRecords(page.getContent().stream().map(convert).collect(Collectors.toList()));
        return d;
    }
    
    public static <P,T> PageDTO<T> from(PageDTO<P> page,Function<P,T> convert){
        PageDTO<T> d=new PageDTO<>();
        d.setPage(page.getPage());
        d.setSize(page.getSize());
        d.setTotal(page.getTotal());
        d.setRecords(page.getRecords().stream().map(convert).collect(Collectors.toList()));
        return d;
    }
}
