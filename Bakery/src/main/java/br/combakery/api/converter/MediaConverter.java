package br.combakery.api.converter;

import br.combakery.api.dto.Media;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import jakarta.persistence.Tuple;
import org.hibernate.jpa.*;
@Converter
public class MediaConverter implements AttributeConverter<Media, Object[]> {

    @Override
    public Object[] convertToDatabaseColumn(Media media) {
        Object[] values = new Object[3];
        values[0] = media.getPedidoId();
        values[1] = media.getMedia();
        values[2] = media.getTotalAvaliacoes();
        return values;
    }

    @Override
    public Media convertToEntityAttribute(Object[] values) {
        Long pedidoId = (Long) values[0];
        Double media = (Double) values[1];
        Long totalAvaliacoes = (Long) values[2];
        return new Media(pedidoId, media, totalAvaliacoes);
    }
}
