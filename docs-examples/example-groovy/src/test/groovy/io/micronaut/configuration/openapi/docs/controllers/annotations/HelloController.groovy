package io.micronaut.configuration.openapi.docs.controllers.annotations

import io.micronaut.http.MediaType

// tags::imports[]
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import reactor.core.publisher.Mono

import javax.validation.constraints.NotBlank
// end::imports
// tag::clazz[]
@Controller("/")
class HelloController {

    /**
     * @param name The person's name
     * @return The greeting message
     */
    @Get(uri="/greetings/{name}", produces= MediaType.TEXT_PLAIN)
    @Operation(summary = "Greets a person",
            description = "A friendly greeting is returned"
    )
    @ApiResponse(
            content = @Content(mediaType = "text/plain",
                    schema = @Schema(type="string"))
    )
    @ApiResponse(responseCode = "400", description = "Invalid Name Supplied")
    @ApiResponse(responseCode = "404", description = "Person not found")
    @Tag(name = "greeting")
    Mono<String> greetings(@Parameter(description="The name of the person") @NotBlank String name) {
        return Single.just("Hello $name, How are you doing?")
    }
}
// end::clazz[]