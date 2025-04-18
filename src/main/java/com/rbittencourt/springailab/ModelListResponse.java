package com.rbittencourt.springailab;

import java.util.List;

public record ModelListResponse(String object, List<GeminiModel> data) {
}
