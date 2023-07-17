package com.hsuweizte.dto;


import com.hsuweizte.domain.Book;
import com.hsuweizte.util.CustomBeanUtils;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.BeanUtils;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {

    private String author;
    @Length(max = 20)
    private String description;
    private String name;
    private Integer status;


    /**
     * 轉換傳輸物件
     *
     * @param book
     */
    public void convertToBook(Book book) {
        new BookConvert().convert(this, book);
    }

    public Book convertToBook() {
        return new BookConvert().convert(this);
    }

    private class BookConvert implements Convert<BookDTO, Book> {

        @Override
        public Book convert(BookDTO bookDTO, Book book) {
            String[] nullPropertyNames = CustomBeanUtils.getNullPropertyNames(bookDTO);
            BeanUtils.copyProperties(bookDTO, book, nullPropertyNames);
            return book;
        }

        @Override
        public Book convert(BookDTO bookDTO) {
            Book book = new Book();
            BeanUtils.copyProperties(bookDTO, book);
            return book;
        }
    }
}
