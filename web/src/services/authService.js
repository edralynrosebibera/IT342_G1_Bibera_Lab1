const API_URL = 'http://localhost:8080/api/auth';

export const registerUser = async (userData) => {
  try {
    const response = await fetch(`${API_URL}/register`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(userData)
    });
    return await response.json();
  } catch (error) {
    return { success: false, message: 'Network error: ' + error.message };
  }
};

export const loginUser = async (credentials) => {
  try {
    const response = await fetch(`${API_URL}/login`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(credentials)
    });
    return await response.json();
  } catch (error) {
    return { success: false, message: 'Network error: ' + error.message };
  }
};

export const testBackend = async () => {
  try {
    const response = await fetch(`${API_URL}/test`);
    return await response.text();
  } catch (error) {
    return 'Backend not running';
  }
};
