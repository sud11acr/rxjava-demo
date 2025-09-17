package com.rxjava.business.business.exception;

import lombok.Builder;
import lombok.Getter;


public class ApiException extends RuntimeException {

    private final String systemCode;
    private final String description;
    private final ErrorCategory category;
    private final Throwable cause;
    private final boolean resolved;

    private ApiException(Builder builder) {
        super(builder.description, builder.cause);
        this.systemCode = builder.systemCode;
        this.description = builder.description;
        this.category = builder.category;
        this.cause = builder.cause;
        this.resolved = builder.resolved;
    }

    public String getSystemCode() {
        return systemCode;
    }

    @Override
    public String getMessage() {
        return description;
    }

    public String getDescription() {
        return description;
    }

    public ErrorCategory getCategory() {
        return category;
    }

    @Override
    public synchronized Throwable getCause() {
        return cause;
    }

    public boolean isResolved() {
        return resolved;
    }

    public static Builder builder() {
        return new Builder();
    }

    // ---------------- BUILDER ---------------- //
    public static class Builder {
        private String systemCode;
        private String description;
        private ErrorCategory category;
        private Throwable cause;
        private boolean resolved;

        public Builder systemCode(String systemCode) {
            this.systemCode = systemCode;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder category(ErrorCategory category) {
            this.category = category;
            return this;
        }

        public Builder cause(Throwable cause) {
            this.cause = cause;
            return this;
        }

        public Builder markAsResolved() {
            this.resolved = true;
            return this;
        }

        public ApiException build() {
            return new ApiException(this);
        }
    }
}
