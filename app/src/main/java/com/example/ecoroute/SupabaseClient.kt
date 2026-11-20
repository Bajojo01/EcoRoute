package com.example.ecoroute

import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.auth.Auth

// single client shared across the app
val supabase = createSupabaseClient(
    supabaseUrl = "https://nspqrdymzwlerwmkayct.supabase.co",
    supabaseKey = "sb_publishable_R2FsV8KTEhBJ3WAzNN7qLg_OPyADVCi"
) {
    install(Auth)
    install(Postgrest) {
        defaultSchema = "eco_route" // your tables are not in public
    }
}