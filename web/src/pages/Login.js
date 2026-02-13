import { useState } from "react"
import { useNavigate } from "react-router-dom"
import { loginUser } from "../services/authService"
import "../assets/css/login.css"

function Login() {
  const [email, setEmail] = useState("")
  const [password, setPassword] = useState("")
  const [loading, setLoading] = useState(false)
  const [error, setError] = useState("")
  const navigate = useNavigate()

  const handleSubmit = async (e) => {
    e.preventDefault()
    setLoading(true)
    setError("")

    try {
      const result = await loginUser({ email, password })
      
      if (result.success) {
        localStorage.setItem('user', JSON.stringify(result))
        localStorage.setItem("token", result.token)
        navigate("/dashboard")
      } else {
        setError(result.message || "Login failed")
      }
    } catch (err) {
      setError("An error occurred. Please try again.")
    } finally {
      setLoading(false)
    }
  }

  return (
    <section>
      <form onSubmit={handleSubmit}>
        <h1>Login</h1>

        <div className="inputbox">
          <ion-icon name="mail-outline"></ion-icon>
          <input
            type="email"
            required
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
          <label>Email</label>
        </div>

        <div className="inputbox">
          <ion-icon name="lock-closed-outline"></ion-icon>
          <input
            type="password"
            required
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
          <label>Password</label>
        </div>

        <button type="submit" disabled={loading}>{loading ? "Signing in..." : "Sign-in"}</button>

        {error && <div style={{ color: "red", marginTop: "10px" }}>{error}</div>}

        <div className="register">
          Don't have an account yet? <a href="/signup">Sign-up</a>
        </div>
      </form>

      <div className="spinner" style={{ display: "none" }}></div>
    </section>
  )
}

export default Login
