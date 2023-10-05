package com.nguyenanhthu.ptm.security.services;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class TokenService {
    private Set<String> invalidatedTokens = new HashSet<>();
    private Set<String> passwordResetTokens = new HashSet<>();

    public void invalidateToken(String token) {
        invalidatedTokens.add(token);
    }

    public boolean isTokenInvalidated(String token) {
        return invalidatedTokens.contains(token);
    }

    public void removeInvalidatedToken(String token) {
        invalidatedTokens.remove(token);
    }

    public boolean isTokenValidForPasswordReset(String token) {
        return !invalidatedTokens.contains(token) && passwordResetTokens.contains(token);
    }
}