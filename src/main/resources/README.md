# Configuration Setup

## Initial Setup

1. **Copy the template file:**
   ```bash
   cp _config.properties.template _config.properties
   ```

2. **Update your credentials** in `_config.properties`:
   ```properties
   test_user_username=your-chess-email@example.com
   test_user_email=your-chess-email@example.com
   test_user_password=your-password
   ```

3. **Run tests:**
   ```bash
   mvn clean test -Dsuite=smoke
   ```

## Important Notes

- `_config.properties` is in `.gitignore` and will **not** be committed to Git
- Your credentials are kept **private** on your local machine
- The template file `_config.properties.template` is committed as a reference
- Never commit the actual `_config.properties` file with real credentials

## Configuration Options

### Selenium Settings
```properties
selenium_url=http://localhost:4444/wd/hub
browser=chrome
explicit_timeout=30
```

### Parallel Execution
```properties
thread_count=3  # Number of parallel test threads
```

### Test Credentials
```properties
test_user_username=your-email@example.com
test_user_password=your-password
```

For more details, see the main project README.
