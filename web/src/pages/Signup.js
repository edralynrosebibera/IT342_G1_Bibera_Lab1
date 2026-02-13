import { useState } from "react"
import { useNavigate } from "react-router-dom"
import { registerUser } from "../services/authService"
import "../assets/css/signup.css"

function Signup() {
  const [form, setForm] = useState({
    firstName: "",
    lastName: "",
    email: "",
    password: "",
    confirmPassword: ""
  })
  const [loading, setLoading] = useState(false)
  const [error, setError] = useState("")
  const navigate = useNavigate()

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value })
  }

  const handleSubmit = async (e) => {
    e.preventDefault()
    setLoading(true)
    setError("")

    if (form.password !== form.confirmPassword) {
      setError("Passwords do not match")
      setLoading(false)
      return
    }

    if (form.password.length < 6) {
      setError("Password must be at least 6 characters")
      setLoading(false)
      return
    }

    try {
      const result = await registerUser(form)
      
      if (result.success) {
        alert("Registration successful! Please log in.")
        navigate("/login")
      } else {
        setError(result.message || "Registration failed")
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
        <h1>Sign Up</h1>

        <div className="inputbox">
          <ion-icon name="person-outline"></ion-icon>
          <input name="firstName" required onChange={handleChange} />
          <label>First Name</label>
        </div>

        <div className="inputbox">
          <ion-icon name="person-outline"></ion-icon>
          <input name="lastName" required onChange={handleChange} />
          <label>Last Name</label>
        </div>

        <div className="inputbox">
          <ion-icon name="mail-outline"></ion-icon>
          <input type="email" name="email" required onChange={handleChange} />
          <label>Email</label>
        </div>

        <div className="inputbox">
          <ion-icon name="lock-closed-outline"></ion-icon>
          <input type="password" name="password" required onChange={handleChange} />
          <label>Password</label>
        </div>

        <div className="inputbox">
          <ion-icon name="lock-closed-outline"></ion-icon>
          <input type="password" name="confirmPassword" required onChange={handleChange} />
          <label>Confirm Password</label>
        </div>

        <button type="submit" disabled={loading}>{loading ? "Signing up..." : "Sign-up"}</button>

        {error && <div style={{ color: "red", marginTop: "10px" }}>{error}</div>}

        <div className="register">
          Already have an account? <a href="/login">Login</a>
        </div>
      </form>
    </section>
  )
}

export default Signup