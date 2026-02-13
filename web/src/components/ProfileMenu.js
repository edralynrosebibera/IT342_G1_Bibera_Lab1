import { useState, useEffect } from "react"
import {
  User,
  Phone,
  Pencil,
  X,
  Check,
} from "lucide-react"
import "../assets/css/profileMenu.css"

function ProfileMenu({ profile, onSave }) {
  const [open, setOpen] = useState(false)
  const [editing, setEditing] = useState(false)
  const [editProfile, setEditProfile] = useState(profile)

  useEffect(() => {
    setEditProfile(profile)
  }, [profile])

  const initials =
    profile?.firstName && profile?.lastName
      ? profile.firstName[0] + profile.lastName[0]
      : ""

  const handleOpenEdit = () => {
    setEditProfile(profile)
    setEditing(true)
  }

  const handleSave = async () => {
    try {
      const token = localStorage.getItem("token")
      const user = JSON.parse(localStorage.getItem("user"))

      const response = await fetch(
        `http://localhost:8080/api/profile/${user.userId}`,
        {
          method: "PUT",
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${token}`,
          },
          body: JSON.stringify(editProfile),
        }
      )

      if (!response.ok) {
        throw new Error("Failed to update profile")
      }

      const updatedProfile = await response.json()

      // IMPORTANT: merge old user + updated profile
      const mergedUser = {
        ...user,
        ...updatedProfile,
      }

      localStorage.setItem("user", JSON.stringify(mergedUser))

      onSave(mergedUser)

      setEditing(false)
      setOpen(false)
    } catch (error) {
      console.error(error)
      alert("Profile update failed")
    }
  }

  const handleLogout = () => {
    localStorage.removeItem("token")
    localStorage.removeItem("user")
    // window.location.href = "/login"
    window.location.replace("/login")
  }

  return (
    <>
      <div className="profile-wrapper">
        <button
          className="profile-trigger"
          onClick={() => setOpen(!open)}
        >
          <div className="avatar">{initials}</div>
          <span className="name">
            {profile?.firstName} {profile?.lastName}
          </span>
        </button>

        {open && (
          <div className="profile-dropdown">
            <div className="profile-info">
              <p className="profile-name">
                {profile?.firstName} {profile?.lastName}
              </p>
            </div>

            <button
              className="dropdown-item"
              onClick={handleOpenEdit}
            >
              <Pencil size={16} /> Edit Profile
            </button>

            <button
              className="dropdown-item logout"
              onClick={handleLogout}
            >
              <X size={16} /> Logout
            </button>
          </div>
        )}
      </div>

      {editing && (
        <div className="modal-overlay">
          <div className="modal">
            <h2>Edit Profile</h2>

            <div className="modal-avatar">{initials}</div>

            <label>
              <span><User size={14} /> First Name</span>
              <input
                value={editProfile?.firstName || ""}
                onChange={(e) =>
                  setEditProfile({
                    ...editProfile,
                    firstName: e.target.value,
                  })
                }
              />
            </label>

            <label>
              <span><User size={14} /> Last Name</span>
              <input
                value={editProfile?.lastName || ""}
                onChange={(e) =>
                  setEditProfile({
                    ...editProfile,
                    lastName: e.target.value,
                  })
                }
              />
            </label>

            <label>
              <span><User size={14} /> Bio</span>
              <input
                value={editProfile?.bio || ""}
                onChange={(e) =>
                  setEditProfile({
                    ...editProfile,
                    bio: e.target.value,
                  })
                }
              />
            </label>

            <label>
              <span><Phone size={14} /> Phone</span>
              <input
                value={editProfile?.phoneNumber || ""}
                onChange={(e) =>
                  setEditProfile({
                    ...editProfile,
                    phoneNumber: e.target.value,
                  })
                }
              />
            </label>

            <div className="modal-actions">
              <button
                className="btn cancel"
                onClick={() => setEditing(false)}
              >
                <X size={16} /> Cancel
              </button>

              <button
                className="btn save"
                onClick={handleSave}
              >
                <Check size={16} /> Save
              </button>
            </div>
          </div>
        </div>
      )}
    </>
  )
}

export default ProfileMenu
