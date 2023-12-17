package com.example.demo.club;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface strona extends PagingAndSortingRepository<Club,Long> {

    Pageable page= PageRequest.of(0,2);

}
