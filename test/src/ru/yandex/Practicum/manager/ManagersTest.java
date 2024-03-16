package ru.yandex.Practicum.manager;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class ManagersTest {

    @Test
    void shouldReturnInitializationCopyOfManagers() {
        assertNotNull(Managers.getDefault());
        assertNotNull(Managers.getDefaultHistory());
    }
}