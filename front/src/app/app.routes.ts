import { Routes } from '@angular/router';
import { roleGuard } from './guards/role-guard';
import { authGuard } from './guards/auth-guard';
import { guestGuard } from './guards/guest-guard';

export const routes: Routes = [
{
    path: '',
    loadComponent: () => import('./components/home/home.component').then(m => m.HomeComponent)
  },
  {
    path: 'auth',
    children: [
      {
        path: 'login',
        loadComponent: () => import('./components/auth/login/login.component').then(m => m.LoginComponent),
        canActivate: [guestGuard]
      },
      {
        path: 'register',
        loadComponent: () => import('./components/auth/register/register.component').then(m => m.RegisterComponent),
        canActivate: [guestGuard]
      }
    ]
  },
  {
    path: 'dashboard',
    loadComponent: () => import('./components/dashboard/dashboard.component').then(m => m.DashboardComponent),
    canActivate: [authGuard] // Protegido: Solo usuarios autenticados
  },
  {
    path: 'seller/activate',
    loadComponent: () => import('./components/seller/activate-seller/activate-seller.component').then(m => m.ActivateSellerComponent),
    canActivate: [authGuard, roleGuard], 
    data: { expectedRole: 'BUYER' } // Protegido: Solo usuarios con rol BUYER
  },
  {
    path: '**',
    redirectTo: '',
    pathMatch: 'full'
  }
];
