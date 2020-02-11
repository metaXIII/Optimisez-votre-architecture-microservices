package com.clientui.exceptions;

import feign.Response;
import feign.codec.ErrorDecoder;

public class CustomErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder errorDecoder = new Default();

    @Override
    public Exception decode(String s, Response response) {
        if (response.status() == 400) {
            return new ProductBadRequestException(
                    "Requête incorrecte "
            );
        } else if (response.status() == 404) {
            return new ProductNotFoundException(
                    "Produit non trouvé "
            );

        }
        return errorDecoder.decode(s, response);
    }
}
