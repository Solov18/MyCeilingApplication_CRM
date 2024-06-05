package ru.gb.diplom.document;

import org.springframework.beans.BeanUtils;

final class DocumentMapper {

    private DocumentMapper() {}



    static DocumentDto toDto(Document document) {
        DocumentDto documentDto = new DocumentDto();
        BeanUtils.copyProperties(document, documentDto);
        return documentDto;
    }

    static Document toEntity(CreateDocumentDto dto) {
        return new Document().builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .created(dto.getCreated())
                .documentCategory(dto.getDocumentCategory())
                .documentStatus(dto.getDocumentStatus())
                .build();
    }

}
