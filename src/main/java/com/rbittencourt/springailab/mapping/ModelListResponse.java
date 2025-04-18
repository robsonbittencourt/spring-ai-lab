package com.rbittencourt.springailab.mapping;

import java.util.List;

public record ModelListResponse(String object, List<GeminiModel> data) {
}
